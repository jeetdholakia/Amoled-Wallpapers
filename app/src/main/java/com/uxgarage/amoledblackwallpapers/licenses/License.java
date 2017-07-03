package com.uxgarage.amoledblackwallpapers.licenses;


import com.uxgarage.wallpaperlibrary.items.InAppBilling;

public class License {

    /*
     * License Checker
     * private static final boolean ENABLE_LICENSE_CHECKER = true; --> enabled
     * Change to private static final boolean ENABLE_LICENSE_CHECKER = false; if you want to disable it
     *
     * NOTE: If you disable license checker you need to remove LICENSE_CHECK permission inside AndroidManifest.xml
     */
    private static final boolean ENABLE_LICENSE_CHECKER = false;

    /*
     * NOTE: If license checker is disabled (above), just ignore this
     *
     * Generate 20 random bytes
     * For easy way, go to https://www.random.org/strings/
     * Set generate 20 random strings
     * Each string should be 2 character long
     * Check numeric digit (0-9)
     * Choose each string should be unique
     * Get string
     */
    private static final byte[] SALT = new byte[]{
            54,
            18,
            82,
            46,
            31,
            40,
            80,
            73,
            16,
            33,
            70,
            00,
            89,
            99,
            90,
            04,
            79,
            92,
            91,
            63

    };

    /*
     * Your license key
     * If your app hasn't published at play store, publish it first as beta, get license key
     */
    private static final String LICENSE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo8cTVUnMz69oMiiF9xM0C9W83//UKNjRLOlvhIXF+NES/lHgFmbwLdpTcR5Fu6pO8ccQycwIVNXUrGfa588R0/8h/QKAUeaNPnYR5tcF8K3iH7axSNkUahRW5mMcm8xd0Uwb4GBlhGunHS4rgVR7oSdWZhaGrxmKBNYW7bzM3jUzngiiO02jSPux74ZYLAi+K3J4hdq5C+k2WY2GpGLvCz/g/kkv2Z5Vyzgtn+9DMRbZQ/qKz4F/XimnZ+6sI6zpxcW740/qvgS1Bl9p3/GbgoA8a0TqSenUiTPGmqGkSRjtkgreaL0LWq8YpSOzy11XvcTTVuL7DqDpowuEs0QmvwIDAQAB";

    /*
     * NOTE: Make sure your app name in project same as app name at play store listing
     * NOTE: Your InApp Purchase will works only after the apk published
     */

    /*
     * NOTE: If donation disabled, just ignored this
     *
     * InApp product id for donation
     * Product name displayed the same as product name displayed at play store
     * So make sure to name it properly
     * Format: new InAppBilling("donation product id")
     */
    private static final InAppBilling[] DONATION_PRODUCTS = new InAppBilling[]{
            new InAppBilling("supportdevelopment")
            /*new InAppBilling("your.product.id"),
            new InAppBilling("your.product.id"),
            new InAppBilling("your.product.id")*/
    };

    public static boolean isLicenseCheckerEnabled() {
        return ENABLE_LICENSE_CHECKER;
    }

    public static String getLicenseKey() {
        return LICENSE_KEY;
    }

    public static byte[] getRandomString() {
        return SALT;
    }

    public static String[] getDonationProductsId() {
        String[] productId = new String[DONATION_PRODUCTS.length];
        for (int i = 0; i < DONATION_PRODUCTS.length; i++) {
            productId[i] = DONATION_PRODUCTS[i].getProductId();
        }
        return productId;
    }

}
