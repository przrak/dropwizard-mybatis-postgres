package com.udemy.dropbookmarks;

import com.loginbox.dropwizard.mybatis.MybatisBundle;
import com.udemy.dropbookmarks.core.Bookmark;
import com.udemy.dropbookmarks.health.TemplateHealthCheck;
import com.udemy.dropbookmarks.mappers.BookmarksMapper;
import com.udemy.dropbookmarks.resources.BookmarksResource;
import com.udemy.dropbookmarks.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.ibatis.session.SqlSessionFactory;

public class DropBookmarksApplication extends Application<DropBookmarksConfiguration> {

    private final MybatisBundle<DropBookmarksConfiguration> mybatisBundle
            = new MybatisBundle<DropBookmarksConfiguration>(BookmarksMapper.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DropBookmarksConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new DropBookmarksApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropBookmarks";
    }

    @Override
    public void initialize(final Bootstrap<DropBookmarksConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(mybatisBundle);
    }

    @Override
    public void run(final DropBookmarksConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);

        SqlSessionFactory sessionFactory = mybatisBundle.getSqlSessionFactory();

        environment.jersey().register(
                new BookmarksResource(sessionFactory)
        );
    }

}
