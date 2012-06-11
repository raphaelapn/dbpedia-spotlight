/**
 * Copyright 2011 Pablo Mendes, Max Jakob
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

package org.dbpedia.spotlight.web.rest.output;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.dbpedia.spotlight.model.DBpediaResourceOccurrence;
import org.dbpedia.spotlight.model.Feature;
import org.dbpedia.spotlight.model.SurfaceFormOccurrence;

import java.util.LinkedList;
import java.util.List;

@XStreamAlias("surfaceForm")
public class Spot {

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private int offset;

    @XStreamAsAttribute
    private String type;

    @XStreamImplicit
    private List<Resource> resources;

    public void setName(String name) {
        this.name = name;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public void setResource(Resource resource) {
        List<Resource> r = new LinkedList<Resource>();
        r.add(resource);
        this.resources = r;
    }

    public static Spot getInstance(SurfaceFormOccurrence sfOcc) {
        Spot spot = new Spot();
        spot.setName(sfOcc.surfaceForm().name());
        spot.setOffset(sfOcc.textOffset());
        Feature typeFeature = sfOcc.features().get("type");
        if (typeFeature != null)
            spot.setType(typeFeature.value().toString());
        return spot;
    }

    public static Spot getInstance(DBpediaResourceOccurrence occ) {
        Spot spot = new Spot();
        spot.setName(occ.surfaceForm().name());
        spot.setOffset(occ.textOffset());
        spot.setResource(Resource.getInstance(occ));
        //spot.setType(occ.);
        return spot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
