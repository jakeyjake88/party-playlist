package com.nashss.se.partyplaylist.lambda;

import com.nashss.se.partyplaylist.activity.requests.GetPlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.GetPlaylistResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class GetPlaylistLambda
        extends LambdaActivityRunner<GetPlaylistRequest, GetPlaylistResult>
        implements RequestHandler<LambdaRequest<GetPlaylistRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetPlaylistRequest> input, Context context) {

        return super.runActivity(() -> input.fromPath(path ->
                        GetPlaylistRequest.builder().withId(path.get("playlistId"))
                                .build()), (request, serviceComponent) ->
                        serviceComponent.provideGetPlaylistActivity().handleRequest(request)

        );
    }
}
