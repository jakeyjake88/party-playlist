package com.nashss.se.partyplaylist.lambda;

import com.nashss.se.partyplaylist.activity.requests.AddUpvoteToSongRequest;
import com.nashss.se.partyplaylist.activity.results.AddUpvoteToSongResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddUpvoteToSongLamda extends LambdaActivityRunner<AddUpvoteToSongRequest, AddUpvoteToSongResult>
    implements RequestHandler<LambdaRequest<AddUpvoteToSongRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<AddUpvoteToSongRequest> input, Context context) {
        return super.runActivity(() -> input.fromBody(AddUpvoteToSongRequest.class), (request, serviceComponent) ->
                        serviceComponent.provideAddUpvoteToSongActivity().handleRequest(request)
        );
    }
}
