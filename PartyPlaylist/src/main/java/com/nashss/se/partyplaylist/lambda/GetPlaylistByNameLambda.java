package com.nashss.se.partyplaylist.lambda;

import com.nashss.se.partyplaylist.activity.requests.GetPlaylistByNameRequest;
import com.nashss.se.partyplaylist.activity.results.GetPlaylistByNameResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class GetPlaylistByNameLambda
        extends LambdaActivityRunner<GetPlaylistByNameRequest, GetPlaylistByNameResult>
        implements RequestHandler<LambdaRequest<GetPlaylistByNameRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetPlaylistByNameRequest> input, Context context) {

        return super.runActivity(() -> input.fromPath(path ->
                        GetPlaylistByNameRequest.builder().withPlaylistName(path.get("playlistName"))
                                .build()), (request, serviceComponent) ->
                        serviceComponent.provideGetPlaylistByNameActivity().handleRequest(request)

        );
    }
}
