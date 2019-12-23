package com.willdom.luis.bottlerocket.repositories;

/**
 * Class to represent the store repository working state and store the value of the results.
 *
 * @author Luis Guzman
 */
public class StoreRepositoryState {

    public enum Result {
        SUCCESS,
        ERROR,
        UNDEFINED
    }

    private boolean mIsLoading;
    private Result mResult;
    private String mMessage;

    //===========================================================================
    //                           CONSTRUCTOR
    //===========================================================================

    StoreRepositoryState(boolean mIsLoading, Result mResult, String mMessage) {
        this.mIsLoading = mIsLoading;
        this.mResult = mResult;
        this.mMessage = mMessage;
    }

    //===========================================================================
    //                           GETTERS
    //===========================================================================

    public boolean isIsLoading() {
        return mIsLoading;
    }

    public Result getResult() {
        return mResult;
    }

    public String getMessage() {
        return mMessage;
    }
}
