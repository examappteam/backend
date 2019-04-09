package backend.exam.controllers;

import backend.exam.models.Exam;
import backend.exam.repository.ExamRepository;
import backend.exam.service.ExamServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class ExamControllerTest {

    ExamController examController;

    @Before
    public void before() {
        examController = new ExamController(new ExamRepository() {
            @Override
            public List<Exam> findAll() {
                throw new UnsupportedOperationException();
            }

            @Override
            public List<Exam> findAll(Sort sort) {
                throw new UnsupportedOperationException();
            }

            @Override
            public List<Exam> findAllById(Iterable<Long> iterable) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <S extends Exam> List<S> saveAll(Iterable<S> iterable) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void flush() {
                throw new UnsupportedOperationException();
            }

            @Override
            public <S extends Exam> S saveAndFlush(S s) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void deleteInBatch(Iterable<Exam> iterable) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void deleteAllInBatch() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Exam getOne(Long aLong) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <S extends Exam> List<S> findAll(Example<S> example) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <S extends Exam> List<S> findAll(Example<S> example, Sort sort) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Page<Exam> findAll(Pageable pageable) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <S extends Exam> S save(S s) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Optional<Exam> findById(Long aLong) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean existsById(Long aLong) {
                throw new UnsupportedOperationException();
            }

            @Override
            public long count() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void deleteById(Long aLong) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void delete(Exam exam) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void deleteAll(Iterable<? extends Exam> iterable) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void deleteAll() {
                throw new UnsupportedOperationException();
            }

            @Override
            public <S extends Exam> Optional<S> findOne(Example<S> example) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <S extends Exam> Page<S> findAll(Example<S> example, Pageable pageable) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <S extends Exam> long count(Example<S> example) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <S extends Exam> boolean exists(Example<S> example) {
                throw new UnsupportedOperationException();
            }
        }, new ExamServiceImpl());
    }

    @Test
    public void name() {

    }
}