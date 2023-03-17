package com.coderfromscratch.jsonparsing;

import com.coderfromscratch.jsonparsing.pojo.SimpleTestCasePOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String simpleTestCaseJsonSource = "{  \n" +
            "  \"title\" : \"Coder From Scratch\" , \n" +
            "  \"author\": \"Rui\"\n" +
            "}";

    private String dayScenario1 = "{\n" +
            "  \"date\" : \"2023-12-25\",\n" +
            "  \"name\" : \"Christmas Day\"\n" +
            "\n" +
            "}";

    private String authorBookScenario = "{\n" +
            "\n" +
            "  \"authorName\": \"Rui\",\n" +
            "  \"books\": [\n" +
            "    {\n" +
            "      \"title\": \"titlel\",\n" +
            "      \"inPrint\": true,\n" +
            "      \"publishDate\": \"2019-12-25\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"title2\",\n" +
            "      \"inPrint\": false,\n" +
            "      \"publishDate\": \"2019-01-01\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";


    @Test
    void parse() throws IOException {

        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        assertEquals(node.get("title").asText() , "Coder From Scratch");
    }

    @Test
    void fromJson() throws IOException{

        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        SimpleTestCasePOJO pojo = Json.fromJson(node , SimpleTestCasePOJO.class);

        assertEquals(pojo.getTitle() , "Coder From Scratch");
    }


    @Test
    void toJson(){
        SimpleTestCasePOJO pojo = new SimpleTestCasePOJO();
        pojo.setTitle("Testing 123");

        JsonNode node = Json.toJson(pojo);

        assertEquals(node.get("title").asText(), "Testing 123");
    }

    @Test
    void stingfy() throws JsonProcessingException {
        SimpleTestCasePOJO pojo = new SimpleTestCasePOJO();
        pojo.setTitle("Testing 123");

        JsonNode node = Json.toJson(pojo);

        System.out.println(Json.stingfy(node));
        System.out.println(Json.prettyPrint(node));




    }

    @Test
    void dayTestScenario1() throws IOException{

        JsonNode node = Json.parse(dayScenario1);
        DayPOJO pojo = Json.fromJson(node , DayPOJO.class);

        assertEquals("Mon Dec 25 05:30:00 IST 2023" ,pojo.getDate().toString());

    }

    @Test
    void authorBookScenario1() throws IOException{

        JsonNode node = Json.parse(authorBookScenario);
        AuthorPOJO pojo = Json.fromJson(node , AuthorPOJO.class);

        System.out.println("Author :" + pojo.getAuthorName());
        for(BookPOJO bP : pojo.getBooks()){
            System.out.println("Book : " + bP.getTitle());
            System.out.println("IS In Print? " + bP.isInPrint());
            System.out.println("Date :" + bP.getPublishDate());
        }



    }


}