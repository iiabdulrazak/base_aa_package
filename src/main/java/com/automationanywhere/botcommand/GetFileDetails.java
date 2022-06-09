package com.automationanywhere.botcommand;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

//BotCommand makes a class eligible for being considered as an action.
@BotCommand

//CommandPks adds required information to be dispalable on GUI.
@CommandPkg(
        //Unique name inside a package and label to display.
        name = "GetImageText", label = "[[GetImageText.label]]",
        node_label = "[[GetImageText.node_label]]", description = "[[GetImageText.description]]", icon = "pkg.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "[[GetImageText.return_label]]", return_type = STRING, return_required = true, return_description = "[[GetImageText.return_label_description]]")

public class GetFileDetails {
    //Identify the entry point for the action. Returns a Value<String> because the return type is String.
    @Execute
    public StringValue action(
            //Idx 1 would be displayed first, with a text box for entering the value.
            @Idx(index = "1", type = TEXT)
            //UI labels.
            @Pkg(label = "[[GetImageText.textOutput.label]]")
            //Ensure that a validation error is thrown when the value is null.
            @NotEmpty
                    String imgUrl) throws IOException, InterruptedException {

        //Internal validation, to disallow empty strings. No null check needed as we have NotEmpty on firstString.
        if ("".equals(imgUrl.trim()))
            throw new BotCommandException("Please make sure to enter a valid url");

        //Business logic
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(imgUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        //Return StringValue
        String result = response.body();
        return new StringValue(result);
    }
}
