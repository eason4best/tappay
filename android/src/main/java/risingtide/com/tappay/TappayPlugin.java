package risingtide.com.tappay;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import tech.cherri.tpdirect.api.TPDServerType;
import tech.cherri.tpdirect.api.TPDSetup;

/**
 * TappayPlugin
 */
public class TappayPlugin implements FlutterPlugin, MethodCallHandler, EventChannel.StreamHandler, PluginRegistry.ActivityResultListener, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private Context context;
    private MethodChannel channel;
    private MethodCall methodCall;
    private Result callResult;
    private ActivityPluginBinding activityBinding;
    private final Integer reqCode = 8787;
    private EventChannel eventChannel;
    private EventChannel.EventSink eventSink;


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        context = flutterPluginBinding.getApplicationContext();
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "tappay");
        channel.setMethodCallHandler(this);
        eventChannel = new EventChannel(flutterPluginBinding.getBinaryMessenger(), "tappayEvent");
        eventChannel.setStreamHandler(this);
    }


    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        methodCall = call;
        callResult = result;
        switch (call.method) {
            case "initialize":
                initialize();
                break;
            case "showPayment":
                showPayment();
                break;
            case "linePay":
                linePay();
                break;
            case "redirectToLinePayPage":
                redirectToLinePayPage();
                break;
            case "jkoPay":
                jkoPay();
                break;
            case "redirectToJkoPayPage":
                redirectToJkoPayPage();
                break;
            case "easyWallet":
                easyWallet();
                break;
            case "redirectToEasyWalletPage":
                redirectToEasyWalletPage();
                break;
            case "googlePay":
                googlePay();
                break;
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
        channel = null;
        eventChannel = null;
    }

    private void initialize() {
        Integer appId = methodCall.argument("appId");
        String appKey = methodCall.argument("appKey");
        String serverType = methodCall.argument("serverType");
        assert appId != null;
        try {
            TPDSetup.initInstance(context, appId, appKey, Objects.equals(serverType, "sandbox") ? TPDServerType.Sandbox : TPDServerType.Production);
            callResult.success("Initialize Succeed");
        } catch (Exception e) {
            callResult.error("Tappay Plugin", "Initialize Failed", null);
        }
    }

    private void showPayment() {
        Intent intent = new Intent(activityBinding.getActivity(), PaymentActivity.class);
        Integer appId = methodCall.argument("appId");
        String appKey = methodCall.argument("appKey");
        String serverType = methodCall.argument("serverType");
        HashMap<String, Object> tpPayByPrimeModel = methodCall.argument("tpPayByPrimeModel");

        intent.putExtra("appId", appId);
        intent.putExtra("appKey", appKey);
        intent.putExtra("serverType", serverType);
        intent.putExtra("tpPayByPrimeModel", tpPayByPrimeModel);

        activityBinding.getActivity().startActivityForResult(intent, reqCode);
        callResult.success("SUCCESS");
    }

    private void linePay() {
        Intent intent = new Intent(activityBinding.getActivity(), LinePayActivity.class);
        Integer appId = methodCall.argument("appId");
        String appKey = methodCall.argument("appKey");
        String serverType = methodCall.argument("serverType");
        HashMap<String, Object> tpPayByPrimeModel = methodCall.argument("tpPayByPrimeModel");
        intent.putExtra("appId", appId);
        intent.putExtra("appKey", appKey);
        intent.putExtra("serverType", serverType);
        intent.putExtra("tpPayByPrimeModel", tpPayByPrimeModel);
        activityBinding.getActivity().startActivityForResult(intent, reqCode);
        callResult.success("SUCCESS");
    }

    private void redirectToLinePayPage() {
        Intent intent = new Intent(activityBinding.getActivity(), LinePayReturnActivity.class);
        String paymentUrl = methodCall.argument("paymentUrl");
        intent.putExtra("paymentUrl", paymentUrl);
        activityBinding.getActivity().startActivityForResult(intent, reqCode);
        callResult.success("SUCCESS");
    }

    private void jkoPay() {
        Intent intent = new Intent(activityBinding.getActivity(), JkoPayActivity.class);
        Integer appId = methodCall.argument("appId");
        String appKey = methodCall.argument("appKey");
        String serverType = methodCall.argument("serverType");
        HashMap<String, Object> tpPayByPrimeModel = methodCall.argument("tpPayByPrimeModel");
        intent.putExtra("appId", appId);
        intent.putExtra("appKey", appKey);
        intent.putExtra("serverType", serverType);
        intent.putExtra("tpPayByPrimeModel", tpPayByPrimeModel);
        activityBinding.getActivity().startActivityForResult(intent, reqCode);
        callResult.success("SUCCESS");
    }

    private void redirectToJkoPayPage() {
        Intent intent = new Intent(activityBinding.getActivity(), JkoPayReturnActivity.class);
        String paymentUrl = methodCall.argument("paymentUrl");
        intent.putExtra("paymentUrl", paymentUrl);
        activityBinding.getActivity().startActivityForResult(intent, reqCode);
        callResult.success("SUCCESS");
    }

    private void easyWallet() {
        Intent intent = new Intent(activityBinding.getActivity(), EasyWalletActivity.class);
        Integer appId = methodCall.argument("appId");
        String appKey = methodCall.argument("appKey");
        String serverType = methodCall.argument("serverType");
        HashMap<String, Object> tpPayByPrimeModel = methodCall.argument("tpPayByPrimeModel");
        intent.putExtra("appId", appId);
        intent.putExtra("appKey", appKey);
        intent.putExtra("serverType", serverType);
        intent.putExtra("tpPayByPrimeModel", tpPayByPrimeModel);
        activityBinding.getActivity().startActivityForResult(intent, reqCode);
        callResult.success("SUCCESS");
    }

    private void redirectToEasyWalletPage() {
        Intent intent = new Intent(activityBinding.getActivity(), EasyWalletReturnActivity.class);
        String paymentUrl = methodCall.argument("paymentUrl");
        intent.putExtra("paymentUrl", paymentUrl);
        activityBinding.getActivity().startActivityForResult(intent, reqCode);
        callResult.success("SUCCESS");
    }

    private void googlePay() {
        Intent intent = new Intent(activityBinding.getActivity(), GooglePayActivity.class);
        Integer appId = methodCall.argument("appId");
        String appKey = methodCall.argument("appKey");
        String serverType = methodCall.argument("serverType");
        HashMap<String, Object> tpPayByPrimeModel = methodCall.argument("tpPayByPrimeModel");
        intent.putExtra("appId", appId);
        intent.putExtra("appKey", appKey);
        intent.putExtra("serverType", serverType);
        intent.putExtra("tpPayByPrimeModel", tpPayByPrimeModel);
        activityBinding.getActivity().startActivityForResult(intent, reqCode);
        callResult.success("SUCCESS");
    }

    @Override
    public void onListen(Object arguments, EventChannel.EventSink events) {
        eventSink = events;
    }

    @Override
    public void onCancel(Object arguments) {

    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == reqCode) {
            if (data == null) return false;
            Map<String, Object> dataFromTapPay = new HashMap<>();
            if (data.hasExtra("data")) {
                dataFromTapPay.put("data", data.getSerializableExtra("data"));
                eventSink.success(dataFromTapPay);
            } else if (data.hasExtra("error")) {
                eventSink.error(data.getStringExtra("error"), null, null);
            } else {
                eventSink.error("Unexpected Error", null, null);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        activityBinding = binding;
        binding.addActivityResultListener(this);
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        activityBinding = null;
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        activityBinding = binding;
        binding.addActivityResultListener(this);
    }

    @Override
    public void onDetachedFromActivity() {
        activityBinding = null;
    }
}
