package com.nashss.se.partyplaylist.lambda;

import com.nashss.se.partyplaylist.activity.requests.CreateHostRequest;
import com.nashss.se.partyplaylist.activity.results.CreateHostResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class CreateHostLambda
    extends LambdaActivityRunner<CreateHostRequest, CreateHostResult>
    implements RequestHandler<LambdaRequest<CreateHostRequest>, LambdaResponse> {

    @Override
        public LambdaResponse handleRequest(LambdaRequest<CreateHostRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromBody(CreateHostRequest.class),
            (request, serviceComponent) ->
                    serviceComponent.provideCreateHostActivity().handleRequest(request)
        );
    }

}
