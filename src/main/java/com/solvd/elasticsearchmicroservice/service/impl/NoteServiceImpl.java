package com.solvd.elasticsearchmicroservice.service.impl;

import com.solvd.elasticsearchmicroservice.domain.Note;
import com.solvd.elasticsearchmicroservice.domain.criteria.NoteCriteria;
import com.solvd.elasticsearchmicroservice.domain.criteria.OrderFields;
import com.solvd.elasticsearchmicroservice.repository.NoteRepository;
import com.solvd.elasticsearchmicroservice.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private static final int PAGE_SIZE = 5;

    private final NoteRepository noteRepository;
    private final ElasticsearchOperations operations;

    @Override
    public Note create(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note update(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public List<Note> findAllByUserId(Long userId) {
        Iterable<Note> iterable = noteRepository.findAllByUserId(userId);
        List<Note> notes = new ArrayList<>();
        iterable.forEach(notes::add);
        return notes;
    }

    @Override
    public List<Note> findByCriteriaOrdered(Integer currentPage, NoteCriteria noteCriteria,
                                            String orderingField) {
        Criteria criteria = new Criteria();
        if (noteCriteria.getKeyWord() != null) {
            criteria.and(Criteria.where("notes.tag.keyword")).contains(noteCriteria.getKeyWord())
                    .or(Criteria.where("notes.theme.keyword")).contains(noteCriteria.getKeyWord())
                    .or(Criteria.where("notes.description.keyword")).contains(noteCriteria.getKeyWord());
        }
        if (noteCriteria.getTags() != null && !noteCriteria.getTags().isEmpty()) {
            criteria.and(Criteria.where("notes.tag.keyword")).in(noteCriteria.getTags());
        }
        if (noteCriteria.getThemes() != null && !noteCriteria.getThemes().isEmpty()) {
            criteria.and(Criteria.where("notes.theme.keyword")).in(noteCriteria.getThemes());
        }
        Pageable pageable = getPageable(currentPage);
        boolean isOrdered = isOrdered(orderingField);
        Query query = new CriteriaQuery(criteria, pageable);
        if (isOrdered) {
            query.addSort(Sort.by(orderingField).ascending());
        }
        SearchHits<Note> notesFiltered = operations.search(query, Note.class, IndexCoordinates.of("notes"));
        System.out.println(notesFiltered.getTotalHits());
        return operations.search(query, Note.class)
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }

    private Pageable getPageable(Integer currentPage) {
        Pageable pageable;
        if (currentPage != null) {
            pageable = PageRequest.of(currentPage, PAGE_SIZE);
        } else {
            pageable = PageRequest.of(0, PAGE_SIZE);
        }
        return pageable;
    }

    private boolean isOrdered(String orderingField) {
        boolean isOrdered = false;
        if (orderingField != null) {
            isOrdered = Arrays.stream(OrderFields.values())
                    .map(Enum::name)
                    .anyMatch(f -> f.equals(orderingField));
        }
        return isOrdered;
    }

    @Override
    public boolean isExistById(Long id) {
        return noteRepository.existsById(id);
    }

    @Override
    public List<Note> findAll() {
        Iterable<Note> iterable = noteRepository.findAll();
        List<Note> notes = new ArrayList<>();
        iterable.forEach(notes::add);
        return notes;
    }

    @Override
    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

}
