-- Both `admin` and `requests` already existed in the live DB but were missing
-- AUTO_INCREMENT on their primary keys (every insert would have failed with
-- "doesn't have a default value"). Both tables were empty, so this was safe
-- to apply directly. `requests.status` also needed a 'completed' value so the
-- system can tell "approved, waiting to be allocated a bed" apart from
-- "approved and a bed has been assigned".
--
-- Applied 2026-07-16. Kept here so a fresh database setup ends up matching.

ALTER TABLE admin MODIFY admin_id INT AUTO_INCREMENT;
ALTER TABLE requests MODIFY request_id INT AUTO_INCREMENT;
ALTER TABLE requests MODIFY status ENUM('pending','approved','rejected','completed') NOT NULL DEFAULT 'pending';
