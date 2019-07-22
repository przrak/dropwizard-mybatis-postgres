package com.udemy.dropbookmarks.resources;

import com.udemy.dropbookmarks.core.Bookmark;
import com.udemy.dropbookmarks.mappers.BookmarksMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/bookmarks")
//https://localhost:8443/bookmarks
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookmarksResource {

    private final SqlSessionFactory sessionFactory;

    public BookmarksResource(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @GET
    public List<Bookmark> getBookmarks() {
        try (SqlSession session = sessionFactory.openSession()) {
            BookmarksMapper bookmarks = session.getMapper(BookmarksMapper.class);
            return bookmarks.getAllBookmarks();
        }
    }

//    private Optional<Bookmark> findIfAuthorized(long bookmarkId, long userId) {
//        Optional<Bookmark> result = dao.findById(bookmarkId);
//        if (result.isPresent() && userId != result.get().getUser().getId()) {
//            throw new ForbiddenException("You are not authorized to see this resource.");
//        }
//
//        return result;
//    }

//    @GET
//    @Path("/{id}")
//    //https://localhost:8443/bookmarks/1
//    @UnitOfWork
//    public Optional<Bookmark> getBookmark(@PathParam("id") LongParam id, @Auth User user) {
//        return findIfAuthorized(id.get(), user.getId());
//    }
//
//    @DELETE
//    @Path("/{id}")
//    @UnitOfWork
//    public Optional<Bookmark> delete(@PathParam("id") LongParam id, @Auth User user) {
//        Optional<Bookmark> bookmark = findIfAuthorized(id.get(), user.getId());
//        if (bookmark.isPresent()) {
//            dao.delete(bookmark.get());
//        }
//        return bookmark;
//    }
//
//    @POST
//    @UnitOfWork
//    public Bookmark saveBookmark(Bookmark bookmark, @Auth User user) {
//        bookmark.setUser(user);
//        return dao.save(bookmark);
//
//    }
}
