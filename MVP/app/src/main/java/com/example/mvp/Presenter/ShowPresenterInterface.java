package com.example.mvp.Presenter;

import com.example.mvp.View.ShowActivityInterface;

public interface ShowPresenterInterface {
    void bindActivity(ShowActivityInterface sa);
    void setImageFromInternet();
}
