package com.nashss.se.partyplaylist.lambda;

import com.nashss.se.partyplaylist.activity.requests.GetPlaylistByIdRequest;
import com.nashss.se.partyplaylist.activity.results.GetPlaylistByIdResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetPlaylistByIdLambda extends LambdaActivityRunner<GetPlaylistByIdRequest, GetPlaylistByIdResult>
        implements RequestHandler<LambdaRequest<GetPlaylistByIdRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetPlaylistByIdRequest> input, Context context) {
        return super.runActivity(() -> input.fromPath(path ->
            GetPlaylistByIdRequest.builder().withPlaylistId(path.get("playlistId"))
                    .build()), (request, serviceComponent) ->
            serviceComponent.provideGetPlaylistByIdActivity().handleRequest(request)

        );
    }
}
