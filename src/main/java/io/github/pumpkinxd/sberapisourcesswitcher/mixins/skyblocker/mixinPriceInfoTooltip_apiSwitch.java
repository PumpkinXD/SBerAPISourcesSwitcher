package io.github.pumpkinxd.sberapisourcesswitcher.mixins.skyblocker;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;


@SuppressWarnings("unused")

@Pseudo
@Mixin(targets="me.xmrvizzy.skyblocker.skyblock.item.PriceInfoTooltip")


public class mixinPriceInfoTooltip_apiSwitch {




    @Dynamic
    @Inject(method = "downloadPrices",at=@At("HEAD"),cancellable = true,remap = false)
    private static void func(String type, CallbackInfoReturnable<JsonObject> cir){
        if(type.matches("lowest bins"))
        {
            try{
                //String lbinurl="https://hysky.de/api/auctions/lowestbins";//not available in my region now
                String lbinurl="https://moulberry.codes/lowestbin.json.gz";
                URL apiAddress = new URL(lbinurl);
                InputStream src = apiAddress.openStream();
                InputStreamReader reader = new InputStreamReader(lbinurl.contains(".gz") ? new GZIPInputStream(src) : src);
                cir.setReturnValue(new Gson().fromJson(reader, JsonObject.class));
            }catch (Throwable e){}

        }

    }

}