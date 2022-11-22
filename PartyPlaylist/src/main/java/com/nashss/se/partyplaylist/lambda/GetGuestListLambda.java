package com.nashss.se.partyplaylist.lambda;

import com.nashss.se.partyplaylist.activity.requests.GetGuestListRequest;
import com.nashss.se.partyplaylist.activity.results.GetGuestListResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetGuestListLambda extends LambdaActivityRunner<GetGuestListRequest, GetGuestListResult>
        implements RequestHandler<LambdaRequest<GetGuestListRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();


    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetGuestListRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () -> input.fromPath(path ->
                    GetGuestListRequest.builder()
                            .withPlaylistId(path.get("playlistId"))
                            .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideGetGuestListActivity().handleRequest(request)
        );
    }
}
