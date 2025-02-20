-- Insert test users
INSERT INTO users (username, password_hash, email) VALUES
('testuser1', 'hash1', 'user1@test.com'),
('testuser2', 'hash2', 'user2@test.com');

-- Insert test documents
INSERT INTO documents (title, description, file_path, uploader_id) VALUES
('Document 1', 'First test document', '/docs/doc1.pdf', 1),
('Document 2', 'Second test document', '/docs/doc2.pdf', 2);

-- Insert test posts
INSERT INTO posts (title, content, author_id) VALUES
('First Post', 'This is the first test post', 1),
('Second Post', 'This is the second test post', 2);

-- Insert test comments
INSERT INTO comments (post_id, user_id, content) VALUES
(1, 2, 'Great post!'),
(2, 1, 'Interesting content');

-- Insert document access
INSERT INTO document_access (document_id, user_id, access_level) VALUES
(1, 2, 'read'),
(2, 1, 'write');
