FROM nginx:1.23.3-alpine

EXPOSE 80 443

# Create folder for SSL files
RUN mkdir -p /etc/nginx/ssl/localhost

# Copy certificate and key
COPY certs/* /etc/nginx/ssl/localhost/

# Copy nginx config
COPY config/localhost.conf /etc/nginx/nginx.conf
