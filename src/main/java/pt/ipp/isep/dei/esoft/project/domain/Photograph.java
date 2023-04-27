package pt.ipp.isep.dei.esoft.project.domain;

import java.net.URI;

public class Photograph {
    private URI uri;

    public Photograph() {
    }

    public Photograph(URI uri) {
        this.uri = uri;
    }

    public URI getUri() {
        return uri;
    }

    public Photograph clone() {
        return new Photograph(this.uri);
    }
}
