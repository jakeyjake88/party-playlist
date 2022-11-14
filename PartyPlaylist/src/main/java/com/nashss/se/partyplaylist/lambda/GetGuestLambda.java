package com.nashss.se.partyplaylist.lambda;
import com.nashss.se.partyplaylist.activity.requests.GetGuestRequest;
import com.nashss.se.partyplaylist.activity.results.GetGuestResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetGuestLambda
        extends  LambdaActivityRunner<GetGuestRequest, GetGuestResult>
        implements RequestHandler<LambdaRequest<GetGuestRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetGuestRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () -> input.fromPath(path ->
                    GetGuestRequest.builder()
                    .withUserId(path.get("userId"))
                    .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideGetGuestActivity().handleRequest(request)
        );
    }
}
