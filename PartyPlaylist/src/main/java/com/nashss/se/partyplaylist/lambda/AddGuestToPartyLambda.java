package com.nashss.se.partyplaylist.lambda;
import com.nashss.se.partyplaylist.activity.requests.AddGuestToPartyRequest;
import com.nashss.se.partyplaylist.activity.results.AddGuestToPartyResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class AddGuestToPartyLambda
    extends LambdaActivityRunner<AddGuestToPartyRequest, AddGuestToPartyResult>
    implements RequestHandler<LambdaRequest<AddGuestToPartyRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<AddGuestToPartyRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromBody(AddGuestToPartyRequest.class),
            (request, serviceComponent) ->
                    serviceComponent.provideAddGuestToPartyActivity().handleRequest(request)
        );
    }
}
