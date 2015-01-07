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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FormattedDateParameterPropertiesProvider extends AbstractBuildParametersProvider {
    private static final Logger LOG = Logger.getInstance(FormattedDateParameterPropertiesProvider.class.getName());

    FormattedDateParameterPropertiesProvider() {
        LOG.info("DatePluginPropertiesProvider initialized.");
    }

    public Map<String, String> getParameters( final SBuild build, final boolean emulationMode) {
        LOG.debug("Processing build: " + build);

        Map<String, String> newParams = new HashMap<String, String>();

        // see if any format has been provided by the user
        String timestampFormat = null;
        if (build.getParametersProvider() != null){
            timestampFormat = build.getParametersProvider().get("build.timestamp.format");
            LOG.debug("Timestamp format provided by user: " + timestampFormat);
        } else {
            LOG.warn("build.getParametersProvider() returned null");
        }

        // if no format provided, use good ol' ISO-1806
        if (timestampFormat == null){
            timestampFormat = "yyyy-MM-dd'T'HH:mmZ";
        }

        LOG.debug("Timestamp format actually used: "+timestampFormat);

        // get the start date
	    Date buildStartDate = (build.getStartDate() != null ? build.getStartDate() : new Date());

        // format it
        String formattedTimestamp = (new SimpleDateFormat(timestampFormat)).format(buildStartDate);

        LOG.debug("Formatted timestamp: "+timestampFormat);

        // return the formatted timestamp
        newParams.put("build.formatted.timestamp", formattedTimestamp);
        return newParams;


    }

}