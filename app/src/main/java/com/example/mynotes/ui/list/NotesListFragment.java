package com.example.mynotes.ui.list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.R;
import com.example.mynotes.domain.Note;
import com.example.mynotes.domain.NotesRepository;
import com.example.mynotes.domain.NotesRepositoryImpl;
import com.example.mynotes.ui.NavDrawable;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NotesListFragment extends Fragment implements NotesListView {

    public static final String NOTE_SELECTED = "NOTE_SELECTED";
    public static final String SELECTED_NOTE_BUNDLE = "SELECTED_NOTE_BUNDLE";

    private LinearLayout container;

    private NotesListPresenter presenter;

    private final NotesRepository repository = new NotesRepositoryImpl();

    private final SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NotesListPresenter(this, NotesRepositoryImpl.getInstance());

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

        RecyclerView list = view.findViewById(R.id.list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);

        list.setLayoutManager(layoutManager);

        NotesListAdapter adapter = new NotesListAdapter();

        list.setAdapter(adapter);

        adapter.setData(repository.getNotes());

        adapter.notifyDataSetChanged();

        adapter.setOnNoteClicked(new NotesListAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(Note note) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(SELECTED_NOTE_BUNDLE, note);

                getParentFragmentManager()
                        .setFragmentResult(NOTE_SELECTED, bundle);
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
                        break;
                    case R.id.action_sort:
                        Toast.makeText(requireContext(), "Sort", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });

        container = view.findViewById(R.id.container);

        presenter.requestNotes();
    }

    @Override
    public void showNotes(List<Note> notes) {
    }
}
