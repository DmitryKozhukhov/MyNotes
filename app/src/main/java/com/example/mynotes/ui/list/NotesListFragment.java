package com.example.mynotes.ui.list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.R;
import com.example.mynotes.domain.FireStoreNotesRepository;
import com.example.mynotes.domain.Note;
import com.example.mynotes.domain.InMemoryNotesRepositoryImpl;
import com.example.mynotes.domain.SharedPreferencesNotesRepository;
import com.example.mynotes.ui.NavDrawable;
import com.example.mynotes.ui.edit.AddNotePresenter;
import com.example.mynotes.ui.edit.EditNoteBottomSheetDialogFragment;
import com.example.mynotes.ui.edit.EditNotePresenter;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {

    public static final String NOTE_SELECTED = "NOTE_SELECTED";
    public static final String SELECTED_NOTE_BUNDLE = "SELECTED_NOTE_BUNDLE";

    private NotesListAdapter adapter;

    private RecyclerView list;

    private LinearLayout container;

    private NotesListPresenter presenter;

    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NotesListPresenter(this, InMemoryNotesRepositoryImpl.getInstance());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new NotesListPresenter(this, FireStoreNotesRepository.INSTANCE);

        list = view.findViewById(R.id.list);

        progressBar = view.findViewById(R.id.progress);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);

        list.setLayoutManager(layoutManager);

        adapter = new NotesListAdapter(this);

        list.setAdapter(adapter);


        view.findViewById(R.id.add_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditNoteBottomSheetDialogFragment.newAddInstance()
                        .show(getParentFragmentManager(), "EditNoteBottomSheetDialogFragment");
            }
        });

        getParentFragmentManager().setFragmentResultListener(EditNotePresenter.KEY_UPDATE, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Note note = result.getParcelable(EditNoteBottomSheetDialogFragment.ARG_NOTE);

                adapter.updateItem(note, presenter.getSelectedNoteIndex());

                adapter.notifyItemChanged(presenter.getSelectedNoteIndex());
            }
        });

        getParentFragmentManager().setFragmentResultListener(AddNotePresenter.KEY_ADD, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Note note = result.getParcelable(EditNoteBottomSheetDialogFragment.ARG_NOTE);

                int index = adapter.addItem(note);

                adapter.notifyItemInserted(index);

                list.smoothScrollToPosition(index);
            }
        });

        presenter.requestNotes();

        adapter.setOnNoteClicked(new NotesListAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(Note note) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(SELECTED_NOTE_BUNDLE, note);

                getParentFragmentManager()
                        .setFragmentResult(NOTE_SELECTED, bundle);
            }

            @Override
            public void onNoteLongClicked(Note note, int position) {
                presenter.setSelectedNote(note);
                presenter.setSelectedNoteIndex(position);
            }
        });

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (requireActivity() instanceof NavDrawable) {
            ((NavDrawable) requireActivity()).setAppBar(toolbar);
        }


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_search:
                        Toast.makeText(requireContext(), "Search", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_sort:
                        Toast.makeText(requireContext(), "Sort", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }
        });

        container = view.findViewById(R.id.container);

        presenter.requestNotes();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void showNotes(List<Note> notes) {
        adapter.setData(notes);

        adapter.notifyDataSetChanged();

    }

    @Override
    public void removeNote(Note note, int index) {

        adapter.removeItem(index);

        adapter.notifyItemRemoved(index);

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        requireActivity().getMenuInflater().inflate(R.menu.menu_notes_list_context, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:

                EditNoteBottomSheetDialogFragment.newUpdateInstance(presenter.getSelectedNote())
                        .show(getParentFragmentManager(), "EditNoteBottomSheetDialogFragment");

                return true;
            case R.id.action_delete:

                presenter.deleteItem();

                return true;
        }
        return super.onContextItemSelected(item);
    }
}
