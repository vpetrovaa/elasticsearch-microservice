package com.solvd.elasticsearchmicroservice.service.impl;

import com.solvd.elasticsearchmicroservice.domain.Note;
import com.solvd.elasticsearchmicroservice.domain.criteria.NoteCriteria;
import com.solvd.elasticsearchmicroservice.domain.criteria.OrderFields;
import com.solvd.elasticsearchmicroservice.repository.NoteRepository;
import com.solvd.elasticsearchmicroservice.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private static final int PAGE_SIZE = 5;

    private static final Logger log = LoggerFactory.getLogger(NoteService.class);


    private final NoteRepository noteRepository;
    private final ElasticsearchOperations operations;

    @Override
    public Note create(Note note) {
        log.info("Create method is called");
        return noteRepository.save(note);
    }

    @Override
    public Note update(Note note) {
        log.info("Update method is called");
        return noteRepository.save(note);
    }

    @Override
    public void delete(Long id) {
        log.info("Delete method is called");
        noteRepository.deleteById(id);
    }

    @Override
    public List<Note> findAllByUserId(Long userId) {
        log.info("FindAllByUserId method is called");
        Iterable<Note> iterable = noteRepository.findAllByUserId(userId);
        List<Note> notes = new ArrayList<>();
        iterable.forEach(notes::add);
        return notes;
    }

    @Override
    public List<Note> findByCriteriaOrdered(Integer currentPage, NoteCriteria noteCriteria,
                                            String orderingField) {
        log.info("FindAllByCriteriaOrdered method is called");
        CriteriaQuery query = new CriteriaQuery(new Criteria());
        noteCriteria.apply(query);
        Pageable pageable = getPageable(currentPage);
        query.setPageable(pageable);
        boolean isOrdered = isOrdered(orderingField);
        if (isOrdered) {
            query.addSort(Sort.by(orderingField).ascending());
        }
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
        log.info("FindAll method is called");
        Iterable<Note> iterable = noteRepository.findAll();
        List<Note> notes = new ArrayList<>();
        iterable.forEach(notes::add);
        return notes;
    }

    @Override
    public Optional<Note> findById(Long id) {
        log.info("FindById method is called");
        return noteRepository.findById(id);
    }

}
