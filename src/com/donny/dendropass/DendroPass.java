package com.donny.dendropass;

import com.donny.dendropass.gui.MainGui;
import com.donny.dendropass.gui.DendroFactory;
import com.donny.dendropass.json.JsonFormattingException;
import com.donny.dendropass.json.JsonItem;
import com.fasterxml.jackson.core.JsonFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DendroPass {
    public static final Charset CHARSET = StandardCharsets.UTF_8;

    public static void main(String[] args) {
        DendroFactory.init();
        new MainGui().setVisible(true);
    }

    public static JsonItem getResource(String path) {
        try (InputStream stream = DendroPass.class.getResourceAsStream("/com/donny/dendropass/resources/" + path)) {
            JsonItem item = JsonItem.digest(new JsonFactory().createParser(stream));
            System.out.println("Resource loaded: " + path);
            return item;
        } catch (IOException e) {
            System.out.println("Resource not located: " + path);
            return null;
        } catch (NullPointerException e) {
            System.out.println("No such resource: " + path);
            return null;
        } catch (JsonFormattingException e) {
            System.out.println("Malformed resource: " + path);
            return null;
        }
    }
}
