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

package com.hazelcast.map.clientv2;

import com.hazelcast.clientv2.KeyBasedClientRequest;
import com.hazelcast.map.MapPortableHook;
import com.hazelcast.map.MapService;
import com.hazelcast.map.TryRemoveOperation;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.Data;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import com.hazelcast.spi.Operation;

import java.io.IOException;

public class MapTryRemoveRequest extends KeyBasedClientRequest {

    protected String name;
    protected Data key;
    protected int threadId;
    protected long timeout;

    public MapTryRemoveRequest() {
    }

    public MapTryRemoveRequest(String name, Data key, int threadId) {
        this.name = name;
        this.key = key;
        this.threadId = threadId;
    }

    public int getFactoryId() {
        return MapPortableHook.F_ID;
    }

    public int getClassId() {
        return MapPortableHook.TRY_REMOVE;
    }

    @Override
    protected Object getKey() {
        return null;
    }

    @Override
    protected Operation prepareOperation() {
        return null;
    }

    public Object process() throws Exception {
        TryRemoveOperation op = new TryRemoveOperation(name, key, timeout);
        op.setThreadId(threadId);
        return null;
    }

    public String getServiceName() {
        return MapService.SERVICE_NAME;
    }

    public void writePortable(PortableWriter writer) throws IOException {
        writer.writeUTF("n", name);
        writer.writeInt("t", threadId);
        writer.writeLong("timeout", timeout);
        // ...
        final ObjectDataOutput out = writer.getRawDataOutput();
        key.writeData(out);
    }

    public void readPortable(PortableReader reader) throws IOException {
        name = reader.readUTF("n");
        threadId = reader.readInt("t");
        timeout = reader.readLong("timeout");
        //....
        final ObjectDataInput in = reader.getRawDataInput();
        key = new Data();
        key.readData(in);
    }

}
