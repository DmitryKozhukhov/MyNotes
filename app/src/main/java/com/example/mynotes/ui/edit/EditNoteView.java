package com.example.mynotes.ui.edit;

import android.os.Bundle;

import androidx.annotation.StringRes;

import com.example.mynotes.domain.Note;

public interface EditNoteView {

    void setButtonTitle(@StringRes int title);

    void setNoteTitle(String title);

    void setNoteDescription(String title);

    void showProgress();

    void hideProgress();

    void setActionButtonEnabled(boolean isEnabled);

    void publishResult(String key, Bundle bundle);

}
