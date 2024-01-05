package com.v1.crello.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardList is a Querydsl query type for BoardList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardList extends EntityPathBase<BoardList> {

    private static final long serialVersionUID = -650284472L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardList boardList = new QBoardList("boardList");

    public final QBoard board;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath title = createString("title");

    public QBoardList(String variable) {
        this(BoardList.class, forVariable(variable), INITS);
    }

    public QBoardList(Path<? extends BoardList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardList(PathMetadata metadata, PathInits inits) {
        this(BoardList.class, metadata, inits);
    }

    public QBoardList(Class<? extends BoardList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

