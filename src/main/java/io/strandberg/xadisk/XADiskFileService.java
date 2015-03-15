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

import org.xadisk.bridge.proxies.interfaces.XAFileInputStream;
import org.xadisk.bridge.proxies.interfaces.XAFileOutputStream;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Niels Peter Strandberg
 */
public interface XADiskFileService {

    XAFileInputStream createXAFileInputStream(File f, boolean lockExclusively) throws XADiskException;

    XAFileInputStream createXAFileInputStream(File f) throws XADiskException;

    XAFileOutputStream createXAFileOutputStream(File f, boolean heavyWrite) throws XADiskException;

    InputStream createInputStream(File f, boolean lockExclusively) throws XADiskException;

    InputStream createInputStream(File f) throws XADiskException;

    OutputStream createOutputStream(File f, boolean heavyWrite) throws XADiskException;

    void createFile(File f, boolean isDirectory) throws XADiskException;

    void deleteFile(File f) throws XADiskException;

    void copyFile(File src, File dest) throws XADiskException;

    void moveFile(File src, File dest) throws XADiskException;

    boolean fileExists(File f, boolean lockExclusively) throws XADiskException;

    boolean fileExists(File f) throws XADiskException;

    boolean fileExistsAndIsDirectory(File f, boolean lockExclusively) throws XADiskException;

    boolean fileExistsAndIsDirectory(File f) throws XADiskException;

    String[] listFiles(File f, boolean lockExclusively) throws XADiskException;

    String[] listFiles(File f) throws XADiskException;

    long getFileLength(File f, boolean lockExclusively) throws XADiskException;

    long getFileLength(File f) throws XADiskException;

    void truncateFile(File f, long newLength) throws XADiskException;
}
