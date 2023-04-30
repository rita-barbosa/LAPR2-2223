package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PhotographTest {

    @Test
    void ensureGetUriWorks() throws URISyntaxException {
        URI uri = new URI("https://www.example.com/images/photo.jpg");
        Photograph photo = new Photograph(uri);

        assertEquals(new URI("https://www.example.com/images/photo.jpg"), photo.getUri());
    }


    @Test
    void ensureCloneWorks() throws URISyntaxException {
        URI uri = new URI("https://www.example.com/images/photo.jpg");
        Photograph photo = new Photograph(uri);
        Photograph clone = photo.clone();

        assertEquals(photo, clone);
    }

    @Test
    void testEqualsSameObject() throws URISyntaxException {
        URI uri = new URI("https://www.example.com/images/photo.jpg");
        Photograph photo = new Photograph(uri);

        assertEquals(photo, photo);
    }

    @Test
    void testEqualsDifferentClass() throws URISyntaxException {
        URI uri = new URI("https://www.example.com/images/photo.jpg");
        Photograph photo = new Photograph(uri);

        assertNotEquals("photo", photo);
    }

    @Test
    void testEqualsNull() throws URISyntaxException {
        URI uri = new URI("https://www.example.com/images/photo.jpg");
        Photograph photo = new Photograph(uri);

        assertNotEquals(null, photo);
    }

    @Test
    void testEqualsDifferentObject() throws URISyntaxException {
        URI uri = new URI("https://www.example.com/images/photo.jpg");
        Photograph photo = new Photograph(uri);

        URI uri1 = new URI("https://www.example.com/images/photo1.jpg");
        Photograph photo1 = new Photograph(uri1);

        assertNotEquals(photo, photo1);
    }

    @Test
    void testEqualsDifferentObjectSameUri() throws URISyntaxException {
        URI uri = new URI("https://www.example.com/images/photo.jpg");
        Photograph photo = new Photograph(uri);

        URI uri1 = new URI("https://www.example.com/images/photo.jpg");
        Photograph photo1 = new Photograph(uri1);

        assertEquals(photo, photo1);
    }

    @Test
    void testEqualsForDifferentObjectType() throws URISyntaxException {
        URI uri = new URI("https://www.example.com/images/photo.jpg");
        Photograph photo = new Photograph(uri);

        assertNotEquals(photo, new Object());
    }

    @Test
    void testHashCodeSameObject() throws URISyntaxException {
        URI uri = new URI("https://www.example.com/images/photo.jpg");
        Photograph photo = new Photograph(uri);

        assertEquals(photo.hashCode(), photo.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() throws URISyntaxException {
        URI uri = new URI("https://www.example.com/images/photo.jpg");
        Photograph photo = new Photograph(uri);

        URI uri1 = new URI("https://www.example.com/images/photo1.jpg");
        Photograph photo1 = new Photograph(uri1);

        assertNotEquals(photo.hashCode(), photo1.hashCode());
    }

}