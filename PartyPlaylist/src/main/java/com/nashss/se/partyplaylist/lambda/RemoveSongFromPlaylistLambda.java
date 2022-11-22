package com.nashss.se.partyplaylist.lambda;

import com.nashss.se.partyplaylist.activity.requests.RemoveSongFromPlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.RemoveSongFromPlaylistResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RemoveSongFromPlaylistLambda
        extends LambdaActivityRunner<RemoveSongFromPlaylistRequest, RemoveSongFromPlaylistResult>
        implements RequestHandler<LambdaRequest<RemoveSongFromPlaylistRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<RemoveSongFromPlaylistRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromBody(RemoveSongFromPlaylistRequest.class),
            (request, serviceComponent) ->
                    serviceComponent.provideRemoveSongFromPlaylistActivity().handleRequest(request)
        );
    }
}
