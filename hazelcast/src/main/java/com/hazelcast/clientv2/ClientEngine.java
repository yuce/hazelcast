/*
 * Copyright (c) 2008-2013, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.clientv2;

import com.hazelcast.cluster.ClusterService;
import com.hazelcast.config.Config;
import com.hazelcast.instance.MemberImpl;
import com.hazelcast.logging.ILogger;
import com.hazelcast.nio.Address;
import com.hazelcast.nio.serialization.Data;
import com.hazelcast.nio.serialization.SerializationService;
import com.hazelcast.partition.PartitionService;

/**
 * @mdogan 4/25/13
 */
public interface ClientEngine {

    PartitionService getPartitionService();

    ClusterService getClusterService();

    SerializationService getSerializationService();

    Config getConfig();

    ILogger getILogger(Class clazz);

    ILogger getILogger(String className);

    Object toObject(Data data);

    Data toData(Object obj);

    Address getMasterAddress();

    Address getThisAddress();

    MemberImpl getLocalMember();

    void sendResponse(ClientEndpoint endpoint, Object response);

}
