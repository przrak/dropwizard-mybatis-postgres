package com.udemy.dropbookmarks.mappers;

import com.udemy.dropbookmarks.core.Bookmark;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookmarksMapper {
    @Select("select * from BOOKMARKS")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "description", column = "description")
    })
    List<Bookmark> getAllBookmarks();
}
