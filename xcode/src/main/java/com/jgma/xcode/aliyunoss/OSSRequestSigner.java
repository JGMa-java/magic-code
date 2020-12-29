package com.jgma.xcode.aliyunoss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.common.auth.Credentials;
import com.aliyun.oss.common.auth.RequestSigner;
import com.aliyun.oss.common.comm.RequestMessage;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.internal.SignUtils;
import com.aliyun.oss.internal.SignV2Utils;

/**
 * @Author: admin
 */
public class OSSRequestSigner implements RequestSigner {

    private String httpMethod;

    /* Note that resource path should not have been url-encoded. */
    private String resourcePath;

    private Credentials creds;

    private SignVersion signatureVersion;

    public OSSRequestSigner(String httpMethod, String resourcePath, Credentials creds, SignVersion signatureVersion) {
        this.httpMethod = httpMethod;
        this.resourcePath = resourcePath;
        this.creds = creds;
        this.signatureVersion = signatureVersion;
    }

    @Override
    public void sign(RequestMessage request) throws ClientException {
        String accessKeyId = creds.getAccessKeyId();
        String secretAccessKey = creds.getSecretAccessKey();

        if (accessKeyId.length() > 0 && secretAccessKey.length() > 0) {
            String signature;

            if (signatureVersion == SignVersion.V2) {
                signature = SignV2Utils.buildSignature(secretAccessKey, httpMethod, resourcePath, request);
                request.addHeader(OSSHeaders.AUTHORIZATION, SignV2Utils.composeRequestAuthorization(accessKeyId,signature, request));
            } else {
                signature = SignUtils.buildSignature(secretAccessKey, httpMethod, resourcePath, request);
                request.addHeader(OSSHeaders.AUTHORIZATION, SignUtils.composeRequestAuthorization(accessKeyId, signature));
            }
        }
    }
}
