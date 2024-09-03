package com.publift.demo.android

import android.app.Application
import com.inmobi.cmp.ChoiceCmp
import com.inmobi.cmp.ChoiceCmpCallback
import com.inmobi.cmp.core.model.ACData
import com.inmobi.cmp.core.model.GDPRData
import com.inmobi.cmp.core.model.gbc.GoogleBasicConsents
import com.inmobi.cmp.core.model.mspa.USRegulationData
import com.inmobi.cmp.model.ChoiceError
import com.inmobi.cmp.model.NonIABData
import com.inmobi.cmp.model.PingReturn
import com.publift.fuseappsdk.FuseSDK
import com.publift.fuseappsdk.utils.FuseLoggingUtil

class DemoApplication : Application() {
    private val cmpCallback = object : ChoiceCmpCallback {
        override fun onCCPAConsentGiven(consentString: String) {
            FuseLoggingUtil.d("[InMobiCMP] CCPA consent given: $consentString")
        }

        override fun onCmpError(error: ChoiceError) {
            FuseLoggingUtil.d("[InMobiCMP] Error: $error")
        }

        override fun onCmpLoaded(info: PingReturn) {
            FuseLoggingUtil.d("[InMobiCMP] Loaded: $info")
        }

        override fun onCmpUIShown(info: PingReturn) {
            FuseLoggingUtil.d("[InMobiCMP] UI shown: $info")
        }

        override fun onGoogleBasicConsentChange(consents: GoogleBasicConsents) {
            FuseLoggingUtil.d("[InMobiCMP] Google basic consent change: $consents")
        }

        override fun onGoogleVendorConsentGiven(acData: ACData) {
            FuseLoggingUtil.d("[InMobiCMP] Google vendor consent given: $acData")
        }

        override fun onIABVendorConsentGiven(gdprData: GDPRData) {
            FuseLoggingUtil.d("[InMobiCMP] IAB vendor consent given: $gdprData")
        }

        override fun onNonIABVendorConsentGiven(nonIABData: NonIABData) {
            FuseLoggingUtil.d("[InMobiCMP] Non IAB vendor consent given: $nonIABData")
        }

        override fun onReceiveUSRegulationsConsent(usRegulationData: USRegulationData) {
            FuseLoggingUtil.d("[InMobiCMP] US regulation data: $usRegulationData")
        }

        override fun onUserMovedToOtherState() {
            FuseLoggingUtil.d("[InMobiCMP] User moved to other state")
        }
    }

    override fun onCreate() {
        super.onCreate()

        FuseLoggingUtil.setLoggingLevel(FuseLoggingUtil.LogLevel.DEBUG)
        FuseSDK.init(this)
        //FuseSDK.addListener(this)
        FuseSDK.enableTestMode(this, true)

        val packageName = "com.publift.mobile.demo.android"
        val pCode = "p-PRrmquD1Ggcb1"
        FuseLoggingUtil.d("Initialising [InMobiCmp] for [$packageName] with code [$pCode]")
        ChoiceCmp.startChoice(this, packageId = packageName, pCode = pCode, callback = cmpCallback)
    }
}
