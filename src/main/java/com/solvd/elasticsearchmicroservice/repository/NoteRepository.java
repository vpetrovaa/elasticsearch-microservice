package com.solvd.elasticsearchmicroservice.repository;

import com.solvd.elasticsearchmicroservice.domain.Note;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NoteRepository extends ElasticsearchRepository<Note, Long> {

    Iterable<Note> findAllByUserId(Long userId);

    boolean existsById(Long id);

}
