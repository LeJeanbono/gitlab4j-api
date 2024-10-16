package org.gitlab4j.api;

import java.util.Collections;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Key;

/**
 * See:
 * https://docs.gitlab.com/ee/api/keys.html#get-user-by-fingerprint-of-ssh-key
 */
public class KeysApi extends AbstractApi {
    public KeysApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * @param fingerprint The md5 hash of a ssh public key with : separating the bytes Or SHA256:$base64hash
     * @return The Key which includes the user who owns the key
     * @throws GitLabApiException If anything goes wrong
     */
    public Key getUserBySSHKeyFingerprint(String fingerprint) throws GitLabApiException {
        MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>();
        queryParams.put("fingerprint", Collections.singletonList(fingerprint));
        Response response = get(Response.Status.OK, queryParams, "keys");
        return response.readEntity(Key.class);
    }
}
