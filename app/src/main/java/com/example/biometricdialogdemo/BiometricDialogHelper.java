package com.example.biometricdialogdemo;

import android.os.Handler;
import android.os.Looper;

import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.FragmentActivity;

import java.util.concurrent.Executor;

public class BiometricDialogHelper {

    private BiometricPrompt.PromptInfo.Builder promptInfo;

    private FragmentActivity parent;

    private Handler handler = new Handler();

    private Executor executor = runnable -> handler.post(runnable);
    private BiometricPrompt biometricPrompt;

    private BiometricDialogHelper(FragmentActivity fragmentActivity) {
        parent = fragmentActivity;
        promptInfo = new BiometricPrompt.PromptInfo.Builder();
    }

    public static BiometricDialogHelper getInstance(FragmentActivity fragmentActivity) {
        return new BiometricDialogHelper(fragmentActivity);
    }

    public BiometricDialogHelper setAuthenticationCallback(BiometricPrompt.AuthenticationCallback authenticationCallback) {
        biometricPrompt = new BiometricPrompt(parent, executor, authenticationCallback);
        return this;
    }

    public void show() {
        promptInfo.setTitle("生物辨識")
                .setDescription("使用生物辨識")
                .setNegativeButtonText("取消")
                .setConfirmationRequired(false);
        new Handler(Looper.getMainLooper()).postDelayed(() ->
                biometricPrompt.authenticate(promptInfo.build()), 100);

    }
}
