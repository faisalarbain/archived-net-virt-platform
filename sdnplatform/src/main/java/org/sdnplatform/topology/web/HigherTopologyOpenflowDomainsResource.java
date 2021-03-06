/*
 * Copyright (c) 2013 Big Switch Networks, Inc.
 *
 * Licensed under the Eclipse Public License, Version 1.0 (the
 * "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 *      http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.sdnplatform.topology.web;

import java.util.HashMap;
import java.util.Map;


import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.sdnplatform.topology.Cluster;
import org.sdnplatform.topology.IBetterTopologyService;


public class HigherTopologyOpenflowDomainsResource extends ServerResource {
    @Get("json")
    public Map<Long, Cluster> retrieve() {
        IBetterTopologyService topology = 
                (IBetterTopologyService)getContext().getAttributes().
                    get(IBetterTopologyService.class.getCanonicalName());

        Map<Long, Cluster> clusterMap = new HashMap<Long,  Cluster>();

        Map<Long, Object> htNodesMap = topology.getHigherTopologyNodes();

        if (htNodesMap == null) return clusterMap;

        for (long nid: htNodesMap.keySet()) {
            Object x = htNodesMap.get(nid);
            if (x instanceof Cluster)
                clusterMap.put(nid, (Cluster)x);
        }
        return clusterMap;
    }
}
