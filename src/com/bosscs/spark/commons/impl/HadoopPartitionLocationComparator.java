/*
 * Copyright 2016, Jerry Xiong, BOSSCS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bosscs.spark.commons.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Comparator;

import com.bosscs.spark.commons.exception.HadoopIOException;
import com.bosscs.spark.commons.exception.HadoopInstantiationException;

/**
 * Given a list of names of machines, this comparator tries as much as he can
 * to put the hostname of the local machine on the first position of the list.
 */
public class HadoopPartitionLocationComparator implements Comparator<String> {
    private final InetAddress hostname;

    /**
     * Default constructor. Automatically tries to resolve the name of the local machine.
     */
    public HadoopPartitionLocationComparator() {
        try {
            this.hostname = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new HadoopInstantiationException(e);
        }
    }

    /**
     * Constucts a comparator using as the name of the local machine the hostname provided.
     *
     * @param hostname the host name of the current machine.
     */
    public HadoopPartitionLocationComparator(String hostname) {
        try {
            this.hostname = InetAddress.getByName(hostname);
        } catch (UnknownHostException e) {
            throw new HadoopInstantiationException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(String loc1, String loc2) {
        int result = 0;

        try {
            InetAddress addr1 = InetAddress.getByName(loc1);
            InetAddress addr2 = InetAddress.getByName(loc2);

            if (addr1 == addr2) {
                result = 0;
            } else if (addr1.isLoopbackAddress()) {
                result = -1;
            } else if (addr2.isLoopbackAddress()) {
                result = 1;
            } else if (addr1.getHostAddress().equals(hostname.getHostAddress())) {
                result = -1;
            } else if (addr2.getHostAddress().equals(hostname.getHostAddress())) {
                result = 1;
            }

        } catch (UnknownHostException e) {
            throw new HadoopIOException(e);
        }

        return result;
    }

    /**
     * @return the host name of the local machine.
     */
    public InetAddress getHostname() {
        return hostname;
    }
}
