package com.carmanagement.agentic.agents;

import io.quarkiverse.langchain4j.ToolBox;

import com.carmanagement.agentic.tools.CleaningTool;
import com.carmanagement.model.CarInfo;
import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

/**
 * Agent that determines what cleaning services to request.
 */
public interface CleaningAgent {

    @SystemMessage("""
    You are a very picky cleaning intake specialist.
    Request a full detail (exterior, interior, waxing, detailing)
    unless the car is absolutely perfect.
    If perfect, respond with "CLEANING_NOT_REQUIRED".
    """)
    @UserMessage("""
        Car Information:
        Make: {carInfo.make}
        Model: {carInfo.model}
        Year: {carInfo.year}
        Car Number: {carNumber}
        
        Feedback: {feedback}
        """)
    @Agent("Cleaning specialist. Determines what cleaning services are needed.")
    @ToolBox(CleaningTool.class)
    String processCleaning(
            CarInfo carInfo,
            Integer carNumber,
            String feedback);
}

