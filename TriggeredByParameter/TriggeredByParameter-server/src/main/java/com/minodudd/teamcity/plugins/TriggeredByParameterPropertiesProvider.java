/*
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
package com.minodudd.teamcity.plugins;

import com.intellij.openapi.diagnostic.Logger;
import jetbrains.buildServer.serverSide.SBuild;
import jetbrains.buildServer.serverSide.parameters.AbstractBuildParametersProvider;

import java.util.HashMap;
import java.util.Map;

public class TriggeredByParameterPropertiesProvider extends AbstractBuildParametersProvider {
    private static final Logger LOG = Logger.getInstance(TriggeredByParameterPropertiesProvider.class.getName());

    TriggeredByParameterPropertiesProvider() {
        LOG.info("TriggeredByParameterPropertiesProvider initialized.");
    }

    public Map<String, String> getParameters( final SBuild build, final boolean emulationMode) {
        LOG.debug("Processing build: " + build);

        Map<String, String> newParams = new HashMap<String, String>();

        // get triggeredBy
        String triggeredBy = build.getTriggeredBy().toString();

        // get triggeredByUser
        String triggeredByUser = null;
        if (build.getTriggeredBy().getUser() != null) {
            triggeredByUser = build.getTriggeredBy().getUser().getUsername();
        } else {
            LOG.warn("build.getTriggeredBy().getUser() is NULL");
            triggeredByUser = "n/a";
        }

        LOG.debug("build.triggeredBy: "+triggeredBy);
        LOG.debug("build.triggeredBy.username: "+triggeredByUser);

        // return the parameters
        newParams.put("build.triggeredBy", triggeredBy);
        newParams.put("build.triggeredBy.username", triggeredByUser);
        return newParams;
    }

}