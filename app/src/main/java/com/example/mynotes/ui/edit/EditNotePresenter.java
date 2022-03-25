package com.example.mynotes.ui.edit;

import android.os.Bundle;

import com.example.mynotes.BuildConfig;
import com.example.mynotes.R;
import com.example.mynotes.domain.Callback;
import com.example.mynotes.domain.Note;
import com.example.mynotes.domain.NotesRepository;

public class EditNotePresenter extends AbstractNotePresenter{

    public static final String KEY_UPDATE = "KEY_UPDATE";

    private Note toEdit;

    public EditNotePresenter(EditNoteView view, NotesRepository repository, Note note) {
        super(view, repository);

        toEdit = note;

        title = toEdit.getTitle();
        content = toEdit.getContent();
    }

    @Override
    void refresh() {
        view.setButtonTitle(R.string.btn_update);
        view.setNoteTitle(toEdit.getTitle());
        view.setNoteDescription(toEdit.getContent());
    }

    @Override
    void onActionButtonClicked() {
        view.showProgress();

        repository.update(toEdit.getId(), title, content, new Callback<Note>() {
            @Override
            public void onSuccess(Note data) {
                view.hideProgress();

                Bundle bundle = new Bundle();
                bundle.putParcelable(EditNoteBottomSheetDialogFragment.ARG_NOTE, data);

                view.publishResult(KEY_UPDATE, bundle);
            }
        });
    }
}
