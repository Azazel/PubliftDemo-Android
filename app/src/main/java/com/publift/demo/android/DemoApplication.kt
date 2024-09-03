package com.publift.demo.android

import android.app.Application
import android.util.Log
import com.inmobi.cmp.ChoiceCmp
import com.inmobi.cmp.ChoiceCmpCallback
import com.inmobi.cmp.core.model.ACData
import com.inmobi.cmp.core.model.GDPRData
import com.inmobi.cmp.core.model.gbc.GoogleBasicConsents
import com.inmobi.cmp.core.model.mspa.USRegulationData
import com.inmobi.cmp.model.ChoiceError
import com.inmobi.cmp.model.NonIABData
import com.inmobi.cmp.model.PingReturn

class DemoApplication : Application() {
    private val cmpCallback = object : ChoiceCmpCallback {
        override fun onCCPAConsentGiven(consentString: String) {
            Log.d("InMobiCMP","CCPA consent given: $consentString")
        }

        override fun onCmpError(error: ChoiceError) {
            Log.d("InMobiCMP","Error: $error")
        }

        override fun onCmpLoaded(info: PingReturn) {
            Log.d("InMobiCMP","Loaded: $info")
        }

        override fun onCmpUIShown(info: PingReturn) {
            Log.d("InMobiCMP","UI shown: $info")
        }

        override fun onGoogleBasicConsentChange(consents: GoogleBasicConsents) {
            Log.d("InMobiCMP","Google basic consent change: $consents")
        }

        override fun onGoogleVendorConsentGiven(acData: ACData) {
            Log.d("InMobiCMP","Google vendor consent given: $acData")
        }

        override fun onIABVendorConsentGiven(gdprData: GDPRData) {
            Log.d("InMobiCMP","IAB vendor consent given: $gdprData")
        }

        override fun onNonIABVendorConsentGiven(nonIABData: NonIABData) {
            Log.d("InMobiCMP","Non IAB vendor consent given: $nonIABData")
        }

        override fun onReceiveUSRegulationsConsent(usRegulationData: USRegulationData) {
            Log.d("InMobiCMP","US regulation data: $usRegulationData")
        }

        override fun onUserMovedToOtherState() {
            Log.d("InMobiCMP","User moved to other state")
        }
    }

    override fun onCreate() {
        super.onCreate()

        val packageName = "com.publift.mobile.demo.android"
        val pCode = "p-PRrmquD1Ggcb1"
        Log.d("InMobiCMP","Initialising for [$packageName] with code [$pCode]")
        ChoiceCmp.startChoice(this, packageId = packageName, pCode = pCode, callback = cmpCallback)
    }
}
