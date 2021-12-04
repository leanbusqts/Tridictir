package dev.bulean.tridictir;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void replaceString_a() {
        assertEquals("i", Utils.replaceString("a"));
    }

    @Test
    public void replaceString_aeiou() {
        assertEquals("iiiii", Utils.replaceString("aeiou"));
    }

    @Test
    public void replaceString_asdf() {
        assertEquals("isdf", Utils.replaceString("asdf"));
    }

    @Test
    public void replaceString_áèö() {
        assertEquals("íìï", Utils.replaceString("áèö"));
    }

    @Test
    public void replaceString_A() {
        assertEquals("I", Utils.replaceString("A"));
    }

    @Test
    public void replaceString_AEIOU() {
        assertEquals("IIIII", Utils.replaceString("AEIOU"));
    }

    @Test
    public void replaceString_ASDF() {
        assertEquals("ISDF", Utils.replaceString("ASDF"));
    }

    @Test
    public void replaceString_ÁÈÖ() {
        assertEquals("ÍÌÏ", Utils.replaceString("ÁÈÖ"));
    }

    @Test
    public void replaceString_aA() {
        assertEquals("iI", Utils.replaceString("aA"));
    }

    @Test
    public void replaceString_aEioU() {
        assertEquals("iIiiI", Utils.replaceString("aEioU"));
    }

    @Test
    public void replaceString_asdfQWER() {
        assertEquals("isdfQWIR", Utils.replaceString("asdfQWER"));
    }

    @Test
    public void replaceString_áèÍöÜ() {
        assertEquals("íìÍïÏ", Utils.replaceString("áèÍöÜ"));
    }

    @Test
    public void replaceString_AaAaaaA() {
        assertEquals("IiIiiiI", Utils.replaceString("AaAaaaA"));
    }

    @Test
    public void replaceString_AEiOU() {
        assertEquals("IIiII", Utils.replaceString("AEiOU"));
    }

    @Test
    public void replaceString_ASDFqwer() {
        assertEquals("ISDFqwir", Utils.replaceString("ASDFqwer"));
    }

    @Test
    public void replaceString_ÁÈïÖù() {
        assertEquals("ÍÌïÏì", Utils.replaceString("ÁÈïÖù"));
    }
}