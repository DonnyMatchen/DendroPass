package com.donny.dendropass.util;

import com.donny.dendropass.DendroPass;

import java.security.SecureRandom;
import java.util.ArrayList;

public class CharacterSet {
    public record Bracket(int min, int max) {
    }

    public static final Bracket CAPITOL_LETTERS = new Bracket(65, 90),
            LOWERCASE_LETTERS = new Bracket(97, 122),
            NUMBERS = new Bracket(48, 57);

    private final ArrayList<Character> CHARACTERS = new ArrayList<>();

    public CharacterSet(ArrayList<Bracket> brackets) {
        for (Bracket b : brackets) {
            for (int i = b.min; i <= b.max; i++) {
                CHARACTERS.add((char) i);
            }
        }
    }
    public CharacterSet(Bracket bracket) {
        for (int i = bracket.min; i <= bracket.max; i++) {
            CHARACTERS.add((char) i);
        }
    }

    public CharacterSet(ArrayList<Bracket> brackets, String specials) {
        this(brackets);
        for (char c : specials.toCharArray()) {
            CHARACTERS.add(c);
        }
    }

    public String getPassword(SecureRandom random, int byteLength) {
        int x = 0;
        StringBuilder out = new StringBuilder();
        while (x < byteLength) {
            boolean flag = true;
            while (flag) {
                char c = CHARACTERS.get(random.nextInt(0, CHARACTERS.size()));
                if (c > 63743 || c < 55296) {
                    if (Character.isDefined(c)) {
                        out.append(c);
                        flag = false;
                    }
                }
            }
            x = out.toString().getBytes(DendroPass.CHARSET).length;
        }
        return out.toString();
    }
}
