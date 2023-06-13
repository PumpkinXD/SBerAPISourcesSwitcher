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
    private static void func(String type, CallbackInfoReturnable<JsonObject> ci){
        if(type.matches("lowest bins"))
        {
            try{
                String lbinurl="https://hysky.de/api/auctions/lowestbins";
                URL apiAddress = new URL(lbinurl);
                InputStream src = apiAddress.openStream();
                InputStreamReader reader = new InputStreamReader(lbinurl.contains(".gz") ? new GZIPInputStream(src) : src);
                ci.setReturnValue(new Gson().fromJson(reader, JsonObject.class));
            }catch (Throwable e){}

        }

    }
//    @Dynamic("Skyblocker")
//    @Inject(method = "<clinit>", at = @At(value="HEAD",remap = false),remap = false)
//    private void inj(){}



//    /***
//     * crashs with Mixin apply for mod sberapisourcesswitcher failed #sberapisourcesswitcher:modid.mixins.json:skyblocker.mixinPriceInfoTooltip_apiSwitch from mod sberapisourcesswitcher -> me.xmrvizzy.skyblocker.skyblock.item.PriceInfoTooltip: org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException non-static callback method me/xmrvizzy/skyblocker/skyblock/item...
//     * @see "https://mclo.gs/FuEzTml"
//     *
//     * For inject point
//     * @see "http://github.com/SkyblockerMod/Skyblocker/blob/b75a2c66ead760bd2471fec16f6abd6303271269/src/main/java/me/xmrvizzy/skyblocker/skyblock/item/PriceInfoTooltip.java#LL354C78-L354C78"
//     *
//     */
    /*
    @Dynamic("Skyblocker")
    @ModifyArg(
            method = "<clinit>",

            at=@At(
                    value = "INVOKE",
                    //target="Ljava/util/Map;put(Ljava/lang/String;Ljava/lang/String)Ljava/lang/String", //javac says Invalid descriptor, even ending with ";"
                    target="put",
                    ordinal = 3,
                    remap = false
                            ),
            remap = false,
            index=1
        )
private static String func(String arg){return "https://hysky.de/api/auctions/lowestbins";}

*/

}