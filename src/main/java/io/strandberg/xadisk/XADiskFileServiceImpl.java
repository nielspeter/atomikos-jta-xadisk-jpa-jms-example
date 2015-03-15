/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.strandberg.xadisk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xadisk.additional.XAFileInputStreamWrapper;
import org.xadisk.additional.XAFileOutputStreamWrapper;
import org.xadisk.bridge.proxies.interfaces.XAFileInputStream;
import org.xadisk.bridge.proxies.interfaces.XAFileOutputStream;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Niels Peter Strandberg
 */
@Service
public class XADiskFileServiceImpl implements XADiskFileService {

    private XADiskSessionFactory sessionFactory;

    @Autowired
    public XADiskFileServiceImpl(XADiskSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public XAFileInputStream createXAFileInputStream(File f, boolean lockExclusively) throws XADiskException {
        try {
            return sessionFactory.getXASession().createXAFileInputStream(f, lockExclusively);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public XAFileInputStream createXAFileInputStream(File f) throws XADiskException {
        try {
            return sessionFactory.getXASession().createXAFileInputStream(f);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public XAFileOutputStream createXAFileOutputStream(File f, boolean heavyWrite) throws XADiskException {
        try {
            return sessionFactory.getXASession().createXAFileOutputStream(f, heavyWrite);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public InputStream createInputStream(File f, boolean lockExclusively) throws XADiskException {
        try {
            return new XAFileInputStreamWrapper(sessionFactory.getXASession().createXAFileInputStream(f, lockExclusively));
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public InputStream createInputStream(File f) throws XADiskException {
        try {
            return new XAFileInputStreamWrapper(sessionFactory.getXASession().createXAFileInputStream(f));
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OutputStream createOutputStream(File f, boolean heavyWrite) throws XADiskException {
        try {
            return new XAFileOutputStreamWrapper(sessionFactory.getXASession().createXAFileOutputStream(f, heavyWrite));
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createFile(File f, boolean isDirectory) throws XADiskException {
        try {
            sessionFactory.getXASession().createFile(f, isDirectory);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteFile(File f) throws XADiskException {
        try {
            sessionFactory.getXASession().deleteFile(f);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void copyFile(File src, File dest) throws XADiskException {
        try {
            sessionFactory.getXASession().copyFile(src, dest);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void moveFile(File src, File dest) throws XADiskException {
        try {
            sessionFactory.getXASession().moveFile(src, dest);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean fileExists(File f, boolean lockExclusively) throws XADiskException {
        try {
            return sessionFactory.getXASession().fileExists(f, lockExclusively);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean fileExists(File f) throws XADiskException {
        try {
            return sessionFactory.getXASession().fileExists(f);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean fileExistsAndIsDirectory(File f, boolean lockExclusively) throws XADiskException {
        try {
            return sessionFactory.getXASession().fileExistsAndIsDirectory(f, lockExclusively);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean fileExistsAndIsDirectory(File f) throws XADiskException {
        try {
            return sessionFactory.getXASession().fileExistsAndIsDirectory(f);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String[] listFiles(File f, boolean lockExclusively) throws XADiskException {
        try {
            return sessionFactory.getXASession().listFiles(f, lockExclusively);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String[] listFiles(File f) throws XADiskException {
        try {
            return sessionFactory.getXASession().listFiles(f);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public long getFileLength(File f, boolean lockExclusively) throws XADiskException {
        try {
            return sessionFactory.getXASession().getFileLength(f, lockExclusively);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public long getFileLength(File f) throws XADiskException {
        try {
            return sessionFactory.getXASession().getFileLength(f);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void truncateFile(File f, long newLength) throws XADiskException {
        try {
            sessionFactory.getXASession().truncateFile(f, newLength);
        } catch (Exception e) {
            throw new XADiskException(e);
        }
    }


}
