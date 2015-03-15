package io.strandberg.jta.examples;


import io.strandberg.xadisk.XADiskFileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.OutputStream;

@Service
public class PersonService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private XADiskFileService fileService;

    @Transactional(rollbackFor = Exception.class)
    public void registerNewPerson(Person person, boolean fail) throws Exception {
        personRepository.save(person);
        jmsTemplate.convertAndSend("PERSON_CREATED", "{name: '" + person.getName() + "'}");

        // create directory
        File dir = new File("build/tmp/person");
        if (!fileService.fileExistsAndIsDirectory(dir)) {
            fileService.createFile(dir, true);
        }

        // create file
        File file = new File(dir, "" + person.getName() + ".txt");
        fileService.createFile(file, false);

        // write to file
        OutputStream out = fileService.createOutputStream(file, false);
        try {
            IOUtils.write(person.toString(), out, "UTF-8");
        } finally {
            IOUtils.closeQuietly(out);
        }

        if (fail) throw new IllegalStateException("BOOM");
    }


    @Transactional
    public String receivePersonCreatedMessage() {
        return (String) jmsTemplate.receiveAndConvert("PERSON_CREATED");
    }
}

