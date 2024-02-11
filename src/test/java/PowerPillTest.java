import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.*;
import java.util.Random;

class PowerPillTest {
    private static Field[] field;
    private static Constructor<?>[] constructors;
    private static Random rand;
    private static final String[] color = {"Blue", "Red", "Pink", "Purple", "Green"};

    @BeforeAll
    static void beforeAll() {
        rand = new Random();
        field = PowerPill.class.getDeclaredFields();
        constructors = PowerPill.class.getDeclaredConstructors();
    }

    @Test
    @DisplayName("[1] test testDefinedStaticField")
    void testDefinedStaticField(){
        boolean isDone = false;
        for(int index=0; index < field.length && !isDone; index++){
            if (field[index].getName().equals("DEFAULT_POWER")){
                isDone = true;
                assertTrue(Modifier.isStatic(field[index].getModifiers()));
                assertEquals(field[index].getType().getTypeName(),"int");
                try {
                    int expectedIntValue = 10;
                    field[index].setAccessible(true);
                    int actualIntValue = field[index].getInt(null);
                    assertEquals(expectedIntValue, actualIntValue);
                } catch (IllegalAccessException iae){
                    assertTrue(iae.getMessage().contains("Attempt to get int"),iae.getMessage());
                }
            }
        }
        assertTrue(isDone,"Static integer field, DEFAULT_POWER, not defined!");
    }

    @Test
    @DisplayName("[1] test testFieldDeclared_power")
    void testFieldDeclared_power(){
        boolean isDone = false;
        for(int index=0; index < field.length && !isDone; index++){
            if (field[index].getName().equals("power")){
                isDone = true;
                assertEquals(field[index].getType().getTypeName(),"int");
            }
        }
        assertTrue(isDone,"integer field, power, not declared!");
    }

    @Test
    @DisplayName("[1] test testFieldDeclared_name")
    void testFieldDeclared_name(){
        boolean isDone = false;
        for(int index=0; index < field.length && !isDone; index++){
            if (field[index].getName().equals("name")){
                isDone = true;
                assertEquals(field[index].getType().getTypeName(),"java.lang.String");
            }
        }
        assertTrue(isDone,"String field, name, not declared!");
    }

    @Test
    @DisplayName("[1] test testDeclaredConstructor_1")
    void testDeclaredConstructor_1(){
        boolean isDone = false;
        for(int index=0; index < constructors.length && !isDone; index++){
           assertNotEquals(0, constructors[index].getParameterCount(),"Default constructor should not be defined!");
            if (constructors[index].getParameterCount() == 1){
                isDone = true;
                Parameter[] p = constructors[index].getParameters();
                //assertEquals("name", p[0].getName(), "Constructor parameter should be called name!");
                assertEquals("java.lang.String", p[0].getType().getTypeName(), "Constructor parameter type should be a String!");
            }
        }
        assertTrue(isDone,"A value constructor with a string parameter called name is not defined!");

        String c = color[rand.nextInt(color.length)];
        PowerPill p = new PowerPill(c);
        try {
            Field fp = p.getClass().getDeclaredField("name");
            fp.setAccessible(true);
            assertEquals(c, fp.get(p), "In first value constructor: name is not initialized properly!");
            fp = p.getClass().getDeclaredField("power");
            fp.setAccessible(true);
            assertEquals(10, fp.getInt(p), "In first value constructor: power is not initialized properly!");
        } catch (NoSuchFieldException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("[1] test testDeclaredConstructor_2")
    void testDeclaredConstructor_2(){
        boolean isDone = false;
        for(int index=0; index < constructors.length && !isDone; index++){
            if (constructors[index].getParameterCount() == 2){
                isDone = true;
                Parameter[] p = constructors[index].getParameters();
                //assertEquals("name", p[0].getName(), "Constructor's 1st parameter should be called name!");
                assertEquals("java.lang.String", p[0].getType().getTypeName(), "In second value constructor: 1st parameter type should be a String!");
                //assertEquals("power", p[1].getName(), "Constructor's 2nd parameter should be called power!");
                assertEquals("int", p[1].getType().getTypeName(), "In second value constructor: 2nd parameter type should be a int!");
            }
        }

        assertTrue(isDone,"Missing a value constructor with a string parameter called name and int parameter called power is not defined!");

        int power = rand.nextInt(20)+20;
        String c = color[rand.nextInt(color.length)];
        PowerPill p = new PowerPill(c, power);
        try {
            Field fc = p.getClass().getDeclaredField("name");
            Field fp = p.getClass().getDeclaredField("power");
            fc.setAccessible(true);
            fp.setAccessible(true);
            assertEquals(c, fc.get(p),"In second value constructor: name not initialized properly!");
            assertEquals(power, fp.getInt(p),"In second value constructor: power not initialized properly!");
        } catch (NoSuchFieldException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("[1] test getName")
    void getName() {
        for(String expected:  color) {
            PowerPill aPill = new PowerPill(expected);
            String actual = aPill.getName();
            assertEquals(expected,actual,"Expected " + expected + " got "+ actual);
        }
    }

    @Test
    @DisplayName("[1] test getPower")
    void getPower() {
        for(String c: color) {
            int expected = rand.nextInt(100);
            PowerPill aPill = new PowerPill(c,expected);
            int actual = aPill.getPower();
            assertEquals(expected,actual,"Expected " + expected + " got "+ actual);
        }
    }

    @Test
    @DisplayName("[1] test setPower")
    void setPower() {
        for(String c: color) {
            int expected = rand.nextInt();
            PowerPill aPill = new PowerPill(c);
            aPill.setPower(expected);
            try {
                Field fp = aPill.getClass().getDeclaredField("power");
                fp.setAccessible(true);
                int actual = fp.getInt(aPill);
                assertEquals(expected, actual, "Expected " + expected + " got " + actual);
            } catch (IllegalAccessException | NoSuchFieldException e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("[1] test setName")
    void setName() {
        for(String expected: color) {
            PowerPill aPill = new PowerPill("");
            aPill.setName(expected);
            try {
                Field fp = aPill.getClass().getDeclaredField("name");
                fp.setAccessible(true);
                String actual = (String)fp.get(aPill);
                assertEquals(expected,actual,"Expected " + expected + " got "+ actual);
            } catch (IllegalAccessException | NoSuchFieldException e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("[1] test toString")
    void testToString() {
        for(String c: color) {
            int power = rand.nextInt(100);
            PowerPill aPill = new PowerPill(c, power);
            String expected = "PowerPill "+ c + " = " + power;
            assertEquals(expected, aPill.toString());
        }
    }
}