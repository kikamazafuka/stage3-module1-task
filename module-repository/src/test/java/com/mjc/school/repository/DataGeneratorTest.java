package com.mjc.school.repository;

import com.mjc.school.repository.datasource.DataGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataGeneratorTest {

    DataGenerator dataGenerator = DataGenerator.getInstance();
    @Test
    public void testReadFromFile(){

        assertEquals(20, dataGenerator.generateNews().size());
    }

}