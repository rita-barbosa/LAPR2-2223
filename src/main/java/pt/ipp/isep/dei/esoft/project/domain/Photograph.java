package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;

/**
 * The type Photograph.
 */
public class Photograph implements Serializable {
    /**
     * The Photograph's Uri.
     */
    private URI uri;

    /**
     * Instantiates a new Photograph.
     */
    public Photograph() {
    }

    /**
     * Instantiates a new Photograph.
     *
     * @param uri the uri
     */
    public Photograph(URI uri) {
        this.uri = uri;
    }

    /**
     * Gets uri.
     *
     * @return the uri
     */
    public URI getUri() {
        return uri;
    }

    /**
     * Clone photograph.
     *
     * @return the photograph
     */
    public Photograph clone() {
        return new Photograph(this.uri);
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photograph that = (Photograph) o;
        return Objects.equals(uri, that.uri);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(uri);
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "    * " + uri ;
    }
}
